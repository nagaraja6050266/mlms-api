#!/bin/bash

# Database connection details
DB_URL="jdbc:postgresql://localhost:5432/MLMSTest"
DB_HOST="localhost"
DB_PORT="5432"
DB_NAME="MLMSTest"
DB_USER="postgres"
DB_PASSWORD="12345678"

# Export the password for non-interactive psql usage
export PGPASSWORD=$DB_PASSWORD

# Connect to the database and drop all tables
psql -h $DB_HOST -p $DB_PORT -U $DB_USER -d $DB_NAME -c "
DO \$\$
DECLARE
    rec RECORD;
BEGIN
    -- Loop through all tables in the public schema
    FOR rec IN (SELECT tablename FROM pg_tables WHERE schemaname = 'public') LOOP
        EXECUTE 'DROP TABLE IF EXISTS ' || quote_ident(rec.tablename) || ' CASCADE';
    END LOOP;
END
\$\$;
"

# Unset the password variable for security
unset PGPASSWORD

echo "All tables in the database '$DB_NAME' have been deleted."