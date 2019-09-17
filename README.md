## Simple Redis Spring Boot Application

I used this application as a starter to test various features of Spring Boot & Redis.


### Deploy and Run on Pivotal Cloud Foundry (PCF)

This project is ready to be deployed on PCF, the `./manifest.yml`, and `CloudConfig.java` files will manage use 
the service binding from PCF to connect to Redis.

Log in to the Cloud Foundry environment:

```bash
$ cf login -a <url>
```

If you are not already in the proper organization you can create or select it using the following commands:

```bash
$ cf create-org redis-demos

$ cf create-space redis-upgrade -o redis-demos

$ cf target -o redis-demos -s redis-upgrade

```

You are now ready to create a new app, and bind it to a new Redis database.

Find the Redis plan that you want to use for your application:

```bash
$ cf marketplace 

OK

service          plans                                    description                                     broker
app-autoscaler   standard                                 Scales bound applications in response to load   app-autoscaler
p.mysql          db-small                                 Dedicated instances of MySQL                    dedicated-mysql-broker
redislabs        small-redis, medium-redis, large-redis   Enterprise-Grade Redis by Redis Labs            redislabs-broker

```

In my environment I am using the [Redis Enterprise for PCF](https://docs.pivotal.io/partners/redis-labs-enterprise-pack/index.html) package.
You can look at the various plans using: (in my case they are the default one)

```bash
$ cf marketplace -s redislabs

  Getting service plan information for service redislabs as admin...
  OK
  
  service plan   description                                                           free or paid
  small-redis    A small 1GB Redis, no replication or persistence.                     free
  medium-redis   A medium 5GB Redis, replication and data persistence.                 free
  large-redis    A large 10GB Redis with 2 shards, replication and data persistence.   free

```

Create a new Redis Service that will be use later by the application:

```bash
$ cf create-service redislabs  medium-redis my-medium-redis-instance

Creating service instance my-medium-redis-instance in org redis-demos / space redis-upgrade as admin...
OK
```

Build the project for Cloud Foundry

```bash
$  mvn clean package spring-boot:repackage
```

You have now multiple options to deploy and run the application, you can for example use the `manifest.yml` 
to bing the service during the deployment, but for learning purpose, let's do it using the `cf` command line. 

This will be done in 3 steps:

1. Push the application without starting it
1. Bin the newly created RedisLabs service to the application
1. Start the application

```bash
$ cf push --no-start
```


```bash
$ cf bind-service simple-pcf-spring-redis-app my-medium-redis-instance

```

You can look at the services and applications using the commands `cf services` and `cf apps`

Let's now start the application

```bash
$ cf start simple-pcf-spring-redis-app
```

You can find the URL to test the application using:

```bash
$ cf apps
```

Copy the URL and open your browser to go to the application running on PCF.
