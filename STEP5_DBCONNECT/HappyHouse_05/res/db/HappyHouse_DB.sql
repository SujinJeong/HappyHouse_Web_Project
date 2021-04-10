CREATE TABLE `User` (
	`user_id`	INT	NOT NULL,
	`user_email`	VARCHAR(20)	NULL,
	`user_password`	VARCHAR(20)	NULL,
	`user_address`	VARCHAR(50)	NULL,
	`user_register_date`	DATETIME	NULL
);

CREATE TABLE `Favorite` (
	`user_id`	INT	NOT NULL,
	`code`	BIGINT	NOT NULL
);

CREATE TABLE `HouseDeal` (
	`deal_id`	INT	NOT NULL,
	`house_id`	INT	NOT NULL,
	`deal_amound`	INT	NULL,
	`deal_year`	INT	NULL,
	`deal_month`	INT	NULL,
	`deal_day`	DATETIME	NULL,
	`deal_area`	DOUBLE	NULL,
	`type`	INT	NULL,
	`floor`	INT	NULL
);

CREATE TABLE `House` (
	`house_id`	INT	NOT NULL,
	`code2`	BIGINT	NOT NULL,
	`house_name`	VARCHAR(20)	NULL,
	`jibun`	VARCHAR(20)	NULL,
	`lat`	VARCHAR(20)	NULL,
	`lng`	VARCHAR(20)	NULL,
	`build_year`	INT	NULL,
	`img`	VARCHAR(30)	NULL,
	`gu_code`	INT	NULL
);

CREATE TABLE `BaseAddress` (
	`code`	BIGINT	NOT NULL,
	`city`	VARCHAR(30)	NULL,
	`gugun`	VARCHAR(30)	NULL,
	`dong`	VARCHAR(30)	NULL,
	`lat`	VARCHAR(20)	NULL,
	`lng`	VARCHAR(20)	NULL
);

CREATE TABLE `Store` (
	`store_id`	INT	NOT NULL,
	`code`	BIGINT	NOT NULL,
	`type_id`	INT	NOT NULL,
	`store_address`	VARCHAR(30)	NULL,
	`store_num`	VARCHAR(20)	NULL
);

CREATE TABLE `Environment` (
	`code`	BIGINT	NOT NULL,
	`fine`	INT	NULL,
	`ultrafine`	INT	NULL,
	`ozone`	DOUBLE	NULL,
	`no`	DOUBLE	NULL,
	`co`	DOUBLE	NULL
);

CREATE TABLE `StroeType` (
	`type_id`	INT	NOT NULL,
	`type_name`	VARCHAR(20)	NULL
);

CREATE TABLE `Hospital` (
	`hospital_id`	INT	NOT NULL,
	`code`	BIGINT	NOT NULL,
	`type_id`	INT	NOT NULL,
	`hospital_name`	VARCHAR(20)	NULL,
	`hospital_num`	VARCHAR(20)	NULL
);

CREATE TABLE `HospitalType` (
	`tyoe_id`	INT	NOT NULL,
	`type_name`	VARCHAR(20)	NULL
);

ALTER TABLE `User` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`user_id`
);

ALTER TABLE `Favorite` ADD CONSTRAINT `PK_FAVORITE` PRIMARY KEY (
	`user_id`,
	`code`
);

ALTER TABLE `HouseDeal` ADD CONSTRAINT `PK_HOUSEDEAL` PRIMARY KEY (
	`deal_id`
);

ALTER TABLE `House` ADD CONSTRAINT `PK_HOUSE` PRIMARY KEY (
	`house_id`
);

ALTER TABLE `BaseAddress` ADD CONSTRAINT `PK_BASEADDRESS` PRIMARY KEY (
	`code`
);

ALTER TABLE `Store` ADD CONSTRAINT `PK_STORE` PRIMARY KEY (
	`store_id`
);

ALTER TABLE `Environment` ADD CONSTRAINT `PK_ENVIRONMENT` PRIMARY KEY (
	`code`
);

ALTER TABLE `StroeType` ADD CONSTRAINT `PK_STROETYPE` PRIMARY KEY (
	`type_id`
);

ALTER TABLE `Hospital` ADD CONSTRAINT `PK_HOSPITAL` PRIMARY KEY (
	`hospital_id`
);

ALTER TABLE `HospitalType` ADD CONSTRAINT `PK_HOSPITALTYPE` PRIMARY KEY (
	`tyoe_id`
);

ALTER TABLE `Favorite` ADD CONSTRAINT `FK_User_TO_Favorite_1` FOREIGN KEY (
	`user_id`
)
REFERENCES `User` (
	`user_id`
);

ALTER TABLE `Favorite` ADD CONSTRAINT `FK_BaseAddress_TO_Favorite_1` FOREIGN KEY (
	`code`
)
REFERENCES `BaseAddress` (
	`code`
);

ALTER TABLE `Environment` ADD CONSTRAINT `FK_BaseAddress_TO_Environment_1` FOREIGN KEY (
	`code`
)
REFERENCES `BaseAddress` (
	`code`
);

