/*
 * Copyright 2019 Infosys Ltd.
 *
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
 
/*Data for the table `tbl_access_level` */

insert  into `tbl_access_level`(`access_id`,`access_created_date`,`access_updated_date`,`access_level`) values (1,'2017-11-23 19:56:42','2017-11-24 19:56:42','Admin Access');
insert  into `tbl_access_level`(`access_id`,`access_created_date`,`access_updated_date`,`access_level`) values (2,'2017-11-23 19:56:42','2017-11-24 19:56:42','Guest Access');


/*Data for the table `tbl_feature` */

insert  into `tbl_feature`(`ft_id`,`deleteStatus`,`ft_name`) values (1,1,'Configuration');
insert  into `tbl_feature`(`ft_id`,`deleteStatus`,`ft_name`) values (2,1,'Pipeline');
insert  into `tbl_feature`(`ft_id`,`deleteStatus`,`ft_name`) values (3,1,'Execution');
insert  into `tbl_feature`(`ft_id`,`deleteStatus`,`ft_name`) values (4,1,'Visualization');
insert  into `tbl_feature`(`ft_id`,`deleteStatus`,`ft_name`) values (5,1,'Stream Studio');
insert  into `tbl_feature`(`ft_id`,`deleteStatus`,`ft_name`) values (6,1,'Stream Analytics');
insert  into `tbl_feature`(`ft_id`,`deleteStatus`,`ft_name`) values (7,1,'User Access Manager');
insert  into `tbl_feature`(`ft_id`,`deleteStatus`,`ft_name`) values (8,1,'Operational UI Wizard');


/*Data for the table `tbl_feature_access` */

insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (1,'','','',1,1);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (2,'','','',1,2);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (3,'','','',1,3);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (4,'','','',1,4);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (5,'','','',1,5);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (6,'','','',1,6);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (7,'','','',1,7);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (8,'','','',1,8);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (9,'\0','\0','',2,1);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (10,'\0','\0','',2,2);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (11,'\0','\0','',2,3);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (12,'\0','\0','',2,4);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (13,'\0','\0','',2,5);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (14,'\0','\0','\0',2,6);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (15,'\0','\0','\0',2,7);
insert  into `tbl_feature_access`(`feature_access_id`,`feature_delete`,`feature_edit`,`feature_view`,`access_id`,`ft_id`) values (16,'\0','\0','\0',2,8);


/*Data for the table `tbl_role` */

insert  into `tbl_role`(`role_id`,`role_created_date`,`role_updated_date`,`role_delete_status`,`role_status`,`role_desc`,`role_name`,`access_id`) values (1,'2017-11-23 19:56:42','2017-11-24 19:56:42',1,'','Admin Desc','Admin',1);
insert  into `tbl_role`(`role_id`,`role_created_date`,`role_updated_date`,`role_delete_status`,`role_status`,`role_desc`,`role_name`,`access_id`) values (2,'2017-11-23 19:56:42','2017-11-24 19:56:42',1,'','Guest Desc','Guest',2);


/*Data for the table `tbl_user` */

insert  into `tbl_user`(`user_id`,`user_created_date`,`user_updated_date`,`user_delete_status`,`user_status`,`user_desc`,`user_name`,`user_password`) values (1,'2017-11-23 19:56:42','2017-11-24 19:56:42',1,'','Real Time User','realtimeuser','');


/*Data for the table `user_role` */

insert  into `user_role`(`user_id`,`role_id`) values (1,1);
insert  into `user_role`(`user_id`,`role_id`) values (1,2);

