create table oauth_client_details (
  client_id VARCHAR(255) PRIMARY KEY,
  resource_ids VARCHAR(255),
  client_secret VARCHAR(255),
  scope VARCHAR(255),
  authorized_grant_types VARCHAR(255),
  web_server_redirect_uri VARCHAR(255),
  authorities VARCHAR(255),
  access_token_validity INTEGER,
  refresh_token_validity INTEGER,
  additional_information VARCHAR(4096),
  autoapprove tinyint
);

create table oauth_client_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255)
);

create table oauth_access_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication_id VARCHAR(255),
  user_name VARCHAR(255),
  client_id VARCHAR(255),
  authentication BLOB,
  refresh_token VARCHAR(255)
);

create table oauth_refresh_token (
  token_id VARCHAR(255),
  token BLOB,
  authentication BLOB
);

create table oauth_code (
  code VARCHAR(255), authentication BLOB
);

//clientId= canteen , secret = canteen
INSERT INTO fusecanteen.oauth_client_details (client_id, resource_ids, client_secret, scope, authorized_grant_types, web_server_redirect_uri, authorities, access_token_validity, refresh_token_validity, additional_information, autoapprove) VALUES ('canteen', 'canteen', '{bcrypt}$2a$10$zyi0F70jOU16NPFkcGqK3eDJrWrCZvXaBqlqtb17nnhAqsjNeTG.q', 'read,write', 'password,refresh_token', null, 'ROLE_CLIENT', 0, null, null, null);

INSERT INTO fusecanteen.employeepositions (Id, positionLevel, positionName, employeeId, EntryDate, EntryUserId, Status, StatusChangeDate, StatusChangeUserId) VALUES (1, 'Senior', 'Java Developerr', null, '2020-09-10 09:05:26.429000000', 1, 2, '2020-09-10 09:11:54.941000000', 1);