echo "Creating db cursomicroogadb"

sleep 60s

/opt/mssql-tools/bin/sqlcmd -S localhost -U sa -P ${SA_PASSWORD} -d master -i /home/mssql/sql/init.sql