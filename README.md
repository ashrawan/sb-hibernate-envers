
## Hibernate envers

To store history data and versioning of our entity class.

This approach of maintaining data history is known as CDC (Change Data Capture)
Recording the changes of data from one consistent state to another.

#### Techniques for CDC:

1.   Database triggers
    See: [https://wiki.postgresql.org/wiki/Audit_trigger](https://wiki.postgresql.org/wiki/Audit_trigger)
2.   Application-level triggers
    Emulates database triggers at the application level. Hibernate envers

Advantage: don't need to mind database specific syntax for triggers
Disadvantage: Can't log data changes that don't flow through the application

3.   Transaction log 

##### RDBMS uses its own way of maintaining transaction log

-   Oracle - GoldenGate
    
-   SQL Server - built in CDC
    
-   MySQL - 3rd party solutions, like LinkedIn DataBus
    
-   Debezium - (open source project by RedHat, connectors for Oracle, MySQL, PostgreSQL and MongoDB) Also support propagation to Kafka
    
If you are using Native SQL or JPA's CriteriaUpdate/CriteriaDelete operations to manipulate database records, then no Envers will not pickup those changes. That is because Hibernate will not raise an event for those bulk or stateless operations allowing Envers to audit those changes.
``` 
<dependency>
   <groupId>org.hibernate</groupId>
   <artifactId>hibernate-envers</artifactId>
   <version>5.2.5.Final</version>
</dependency>
```
-   Revision Table (Default: REVINFO)
    
-   Audit table for each entity

#### Logging data for revisions

By Default Hibernate enver uses REVINFO  table to log data for revision.
```
CREATE TABLE revinfo  (
   rev integer NOT NULL,
   revtstmp bigint,
   CONSTRAINT revinfo_pkey PRIMARY KEY (rev)
)
```

![](https://lh5.googleusercontent.com/naDZUyu6a9Q3N8WSaGFbjDDQ-nZMLcCgnlOfQyufKbJhbmiH0LUqYoUmTY5NTs9uqShhV78OcMo03cUT8AJxrg1xI-BxLymmvp1cIkv2QveD8dlpbj8sllEdALzSNfM_moi9EnHj)

  
```
@Entity  
@RevisionEntity(CustomRevisionListener.class)  

// Modifying Default "reverifo" table provided by Hibernate envers  
public  class  AuditEnversInfo  extends  DefaultRevisionEntity {  
  
  private String source;  
  
  private  long userId;  
  
  public String getSource() {  
  return source;  
}
```
  
```
public  class  CustomRevisionListener  implements  RevisionListener {  
  
  // Set auditEnversInfo here, Source; like ip, user who made changes    
  @Override  
  public  void  newRevision(Object revisionEntity) {  
    AuditEnversInfo auditEnversInfo = (AuditEnversInfo) revisionEntity;  
    auditEnversInfo.setSource("In application");  
    auditEnversInfo.setUserId(1L);  
  }   
}
```

![](https://lh4.googleusercontent.com/wcAOr5XrfUfRrW9AK7tHqlGxAY0lalnHLIUTqcS3u5AzlQ2WBcqAh3YXX5JSdshe7EklWSd5rJ1_FKAi3wvVCo1T8uoRPsq7yl6JBjLuopfbjlvXLXrVtq9w3P1II64PXr6nsFQ5)

  
Columns

-   Primary key same as original entity
    
-   all audited fields
    
-   revision number (used with primary id column to create combined primary key)
    
-   revision type (type of operation performed on entity) 0- Added, 1- Updated, 2- Deleted
    

##### In Original Entity: Tell which entity or attribute to be audited
```
@Entity  
@Audited  
public  class  Customer {... }
```
  

##### To Read the Revision Information: 
```
AuditReader.getRevisions // all revisions of an entity

AuditReader auditReader = AuditReaderFactory.get(em);  
List<Number> revisionNumbers = auditReader.getRevisions(Entity.class, e.getId());
```
Some of the important annotations used in Hibernate Envers

-   @Audited: we can apply this annotation to either field or a class, when applied to a class, indicates that all of its properties should be audited and when applied to a field, indicates that this field should be audited.
    
-   @NotAudited: When applied to a field, indicates that this field should not be audited.
    
-   @AuditingOverride: The annotation is used to override the auditing behavior of a superclass or single property inherited from super class type, or attribute inside an embedded component.
    
-   @AuditingOverrides: The annotation is used to override the auditing behavior for one or more fields (or properties) inside an embedded component.
    
-   @AuditTable: By default, Hibernate adds the “_AUD” suffix to the table name of the audited entity. We can define a different table name with the @AuditTable annotation or by configuring a different prefix or suffix in the configuration.
    
-   @RevisionNumber: Marks a property which will hold the number of the revision in a revision entity,Values of this property should form a strictly-increasing sequence of numbers. The value of this property won’t be set by Envers. Most of the cases, this should be an auto-generated database-assigned primary id.
    
-   @RevisionTimestamp: Marks a property which will hold the timestamp of the revision in a revision entity, the value of this property will be automatically set by Envers.
    
-   @AuditJoinTable: This annotation is used to audit the entity relations, name of the audit join will be by default to a concatenation of the names of the primary table of the entity owning the association and of the primary table of the entity referenced by the association.
    
-   If we are using Secondary Table(s) in entity, then audit tables for them will be generated in the same way (by adding the prefix and suffix). If you wish to overwrite this behavior, you can use the @SecondaryAuditTable and @SecondaryAuditTables annotations.
    
-   We can override auditing behavior of some fields/properties inherited from @MappedSuperclass or in an embedded component, you can apply the @AuditOverride annotation on the subtype or usage site of the component.
    
-   If parent entity is annotated with @Audited and associates entities are not audited, then it will try to fetch the latest associations, if it not found then it will throw exception, in this case we can @NotFound annotation to ignore the exception.
  
  http://docs.jboss.org/hibernate/orm/current/userguide/html_single/Hibernate_User_Guide.html#envers
