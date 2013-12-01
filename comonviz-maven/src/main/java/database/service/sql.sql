Hibernate: select processact0_.id as id6_0_, processobj1_.ID 
as id6_1_, processact0_2_.creationDateTime as creation2_6_0_, processact0_2_.creationUserId
as creation3_6_0_, processact0_2_.databaseVersionId as database4_6_0_, processact0_2_.modificationDateTime
as modifica5_6_0_, processact0_2_.modificationUserId as modifica6_6_0_, processact0_2_.name
as name6_0_, processact0_1_.discription as discript1_1_0_, processobj1_2_.creationDateTime
as creation2_6_1_, processobj1_2_.creationUserId as creation3_6_1_, processobj1_2_.databaseVersionId 
as database4_6_1_, processobj1_2_.modificationDateTime as modifica5_6_1_, processobj1_2_.modificationUserId
as modifica6_6_1_, processobj1_2_.name as name6_1_, processobj1_1_.discription
as discript1_1_1_, processobj1_.processActivity_id as processA2_3_1_, processobj1_.processActivity_id
as processA2_6_0__, processobj1_.ID as ID3_0__

from ProcessActivity processact0_ 
inner join DataModel processact0_1_ on processact0_.id=processact0_1_.id
inner join Trackable processact0_2_ on processact0_.id=processact0_2_.id
left outer join ProcessObjective processobj1_
on processact0_.id=processobj1_.processActivity_id 
left outer join DataModel processobj1_1_ on processobj1_.ID=processobj1_1_.id
left outer join Trackable processobj1_2_ on processobj1_.ID=processobj1_2_.id
where processact0_2_.name=?