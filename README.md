# Simple Spring Cloud Vault demo

With your project set up, you can install and launch HashiCorp Vault.
If you are using a Mac with homebrew, this is as simple as:

`brew install vault` or `brew upgrade vault`

## Start vault server

After you install Vault, launch it in a console window. 
This command also starts up a server process.

`vault server --dev --dev-root-token-id="00000000-0000-0000-0000-000000000000"`
You should see the following as one of the last output lines:

`[INFO ] core: post-unseal setup complete`

The command above starts Vault in development mode using in-memory storage without 
transport encryption.

## Use vault cli to add secrets to the vault server

Launch another console window to store application configuration in Vault 
using the Vault command line.
First, you need to set two environment variables to point the 
Vault CLI to the Vault endpoint and provide an authentication token.

```shell script
export export VAULT_TOKEN="00000000-0000-0000-0000-000000000000"
export VAULT_ADDR="http://127.0.0.1:8200"
```
Now you can store a configuration key-value pairs inside Vault:

```shell script
vault kv put secret/gs-vault-config example.username=demouser example.password=demopassword
vault kv put secret/gs-vault-config/cloud example.username=clouduser example.password=cloudpassword
```

Now you have written two entries in Vault `secret/gs-vault-config` and `secret/gs-vault-config/cloud`.

### Imp things to note about the key-value pair 
1. Spring Cloud Vault constructs a Vault context path from `spring.application.name` 
   which is `gs-vault-config`
2. appends the profile name (`--spring.profiles.active=cloud`) so enabling the 
   cloud profile will fetch additionally configuration properties from 
   `secret/gs-vault-config/cloud`, 
3. Here, prefix of the configuration property is `example`
Note that this is used in the configuration class as **@ConfigurationProperties("example")**

## Note on use of `bootstrap.properties` instead of `application.properties`
Spring Cloud Configuration integrations use the bootstrap context for their configuration. 
Bootstrap context is configured before spinning up the application context so configuration 
integrations can load and initialize PropertySources that are then used in the application 
context.
As a consequence, rename your `application.properties` to `bootstrap.properties`.

## Run the demo
Run application with default profile -
`./run.sh`

Run application with cloud profile -  
`./run-cloud.sh`

### Cleanup
`pkill vault`