# electricity_billing_system
Electricity billing system project in java, for both admin and user side interface.
Then create database in your mysql server and connect with your application inside the src/Electricity/Conn.java file.


---------------------------------------------------
Database Queries for ELECTRICITY BILLING SYSTEM Project
---------------------------------------------------


1 - Create database with in mysql

create database ebs;

2 - Select the database you just created

use ebs;

3 - Create our first Table in the selected database with name login

create table login(meter_no varchar(20), username varchar(30), name varchar(30), password varchar(20), user varchar(20)); 

4 - Create the second table to store more information of user

create table customer(name varchar(20), meter_no varchar(20), address varchar(50), city varchar(30), state varchar(30), email varchar(40), phone varchar(20));

5 - Create the third table to store the meter information of the user

create table meter_info(meter_no varchar(20), meter_location varchar(20), meter_type varchar(20), phase_code varchar(20), bill_type varchar(20), days varchar(20));

6 - Create the Tax table to store tax related information

create table tax(cost_per_unit varchar(20), meter_rent varchar(20), service_charge varchar(20), service_tax varchar(20), swacch_bharat_cess varchar(20), fixed_tax varchar(20));

7 - Now inset values in the tax table

insert into tax values('9','47','22','57','6','18');

8 - Create Bill table to store electricity bill information of the user

create table bill(meter_no varchar(20), month varchar(30), units varchar(20), totalbill varchar(20), status varchar(20));
