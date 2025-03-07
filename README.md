# GitHub Repositories API

API created as part of a recruitment assignment. 

It allows you to download a list of the user's repositories (excluding forks) and branch information.

## Created with

This API was built using **Quarkus 3.19.2** and **Java 21**.

## Running the application 

1. **Clone Repository**

```shell script
git clone https://github.com/wych0/github-repositories-api.git
cd github-repositories-api
```

2. **Build Application**

```shell script
mvn clean install
```

3. **Run Application**

```shell script
./mvnw quarkus:dev
```

## How to use API?

### List user repositories

- **Method:** `GET`
- **Path:** `/github/{username}/repos`
- **Parameters:**
  - `username` - GitHub username

#### Example request:

```shell script
GET http://localhost:8080/github/octocat/repos
```

#### Example response:

```json
[
  {
    "repoName": "octocat.github.io",
    "ownerLogin": "octocat",
    "branches": [
      {
        "name": "gh-pages",
        "commit": {
          "sha": "c0e4a095428f36b81f0bd4239d353f71918cbef3"
        }
      },
      {
        "name": "master",
        "commit": {
          "sha": "3a9796cf19902af0f7e677391b340f1ae4128433"
        }
      }
    ]
  }
]
```

#### Example response (error):

```json
{
  "status": 404,
  "message": "GitHub user not found"
}
```

## Testing

**Run integration test**

```shell script
mvn test
```





