# json-data-api
Simple way to serve json data with Json Web Token

## TODO
- jwt implementation

## Usage
build

run 
```bash
java -Dendpoints=endpoint1,endpoint2 -DjsonFiles=path/to/your/json1.json,path/to/your/json2.json -jar path/to/your/jarfile.jar

# example:
java -Dendpoints=data -DjsonFiles="../test.json" -jar .\json-api-0.0.1-SNAPSHOT.jar

# example of unsecure start (without jwt):
java -Dendpoints=data -DjsonFiles="../test.json" -D"app.enableJwtAuthentication=false" -jar .\json-api-0.0.1-SNAPSHOT.jar
```

If you do not pass any endpoints, you can test that the api is running by going to localhost:<port>/test


generate tokens by going to the generate token end point and provide an id.