INSERT INTO "PUBLIC"."PAYMENT"( "ID", "VERSION", "AMOUNT", "CURRENCY", "DATE", "DESCRIPTION", "MERCHANTID", "PAYMENTFLOW", "SERVICETYPE", "STATUS", "TRANSACTIONID", "SESSIONID") VALUES ( 1, 1, 3.10,  'USD', TO_TIMESTAMP ( '22/11/2003 13:30:30', 'DD/MM/YYYY HH:MI:SS' ), 'cd', 'MER01', 'AUTHORIZE',   'OTP', 'OPEN',    'TR10000001', 'SID10000001')
INSERT INTO "PUBLIC"."PAYMENT"( "ID", "VERSION", "AMOUNT", "CURRENCY", "DATE", "DESCRIPTION", "MERCHANTID", "PAYMENTFLOW", "SERVICETYPE", "STATUS", "TRANSACTIONID", "SESSIONID") VALUES ( 2, 1, 1.37,  'USD', TO_TIMESTAMP ( '22/11/2007 03:15:51', 'DD/MM/YYYY HH:MI:SS' ), 'cd', 'MER01', 'ACKNOWLEDGE', 'OTP', 'PENDING', 'TR10000002', 'SID10000002')
INSERT INTO "PUBLIC"."PAYMENT"( "ID", "VERSION", "AMOUNT", "CURRENCY", "DATE", "DESCRIPTION", "MERCHANTID", "PAYMENTFLOW", "SERVICETYPE", "STATUS", "TRANSACTIONID", "SESSIONID") VALUES ( 3, 1, 17.35, 'USD', TO_TIMESTAMP ( '05/01/2013 15:03:07', 'DD/MM/YYYY HH:MI:SS' ), 'cd', 'MER01', 'CAPTURE',     'OTP', 'DONE',    'TR10000003', 'SID10000003')
INSERT INTO "PUBLIC"."PAYMENT"( "ID", "VERSION", "AMOUNT", "CURRENCY", "DATE", "DESCRIPTION", "MERCHANTID", "PAYMENTFLOW", "SERVICETYPE", "STATUS", "TRANSACTIONID", "SESSIONID") VALUES ( 4, 1, 10.15, 'USD', TO_TIMESTAMP ( '05/01/2013 17:00:33', 'DD/MM/YYYY HH:MI:SS' ), 'cd', 'MER01', 'CAPTURE',     'OTP', 'DONE',    'TR10000004', 'SID10000004')
INSERT INTO "PUBLIC"."PAYMENT"( "ID", "VERSION", "AMOUNT", "CURRENCY", "DATE", "DESCRIPTION", "MERCHANTID", "PAYMENTFLOW", "SERVICETYPE", "STATUS", "TRANSACTIONID", "SESSIONID") VALUES ( 5, 1, 1.37,  'USD', TO_TIMESTAMP ( '13/09/2001 03:15:51', 'DD/MM/YYYY HH:MI:SS' ), 'cd', 'MER01', 'ACKNOWLEDGE', 'OTP', 'FAILED',  'TR10000005', 'SID10000005')

INSERT INTO "PUBLIC"."MERCHANT"( "ID", "VERSION", "MERCHANTID", "USERNAME", "PASSWORD", "ACTIVE") VALUES ( 1, 1, 'MER01', 'tesco', 'password', 'true')
INSERT INTO "PUBLIC"."MERCHANT"( "ID", "VERSION", "MERCHANTID", "USERNAME", "PASSWORD", "ACTIVE") VALUES ( 2, 1, 'MER02', 'ikea', 'password', 'true')
INSERT INTO "PUBLIC"."MERCHANT"( "ID", "VERSION", "MERCHANTID", "USERNAME", "PASSWORD", "ACTIVE") VALUES ( 3, 1, 'MER03', 'amazon', 'password', 'false')

INSERT INTO "PUBLIC"."CONSUMER"( "ID", "VERSION", "EMAIL", "MSISDN", "BALANCE", "ACTIVE") VALUES ( 1, 1, 'larinde.java@gmail.com', '497417590333',   70000.37, 'true')
INSERT INTO "PUBLIC"."CONSUMER"( "ID", "VERSION", "EMAIL", "MSISDN", "BALANCE", "ACTIVE") VALUES ( 2, 1, 'larinde.soa@gmail.com',  '44497620973131', 30.15, 'true')
