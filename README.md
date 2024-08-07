# Kooby Vue Admin template

*Admin Template with Vue 3 and Kooby (Using PrimeVue/SakaiVue)*

## Sperated projects

- [Kooby API Template](https://github.com/jonaskahn/kooby-api-template)
- [Enhanced Sakai Vue](https://github.com/jonaskahn/enhanced-sakai-vue)

## My similar projects

- [Spring Vue Admin Template (Kotlin)](https://github.com/jonaskahn/spring-vue-admin-template)

## Getting started

### 1. Run database

```shell
docker compose -f compose/dev.compose.yaml up -d
```

### 2. [Develop with intelliJ](https://jooby.io/usage/)

### 3. Coding convention

> I do not tie with any coding conventions. Take the best one you want to apply from Google/MS or even default of
> IntelliJ Idea

### 4. Notes

#### 4.1 Response

Client and server should always produce and consume only type of object `Respone`:

- **Success**

```json
{
  "status": 200,
  "message": "Response message from backend",
  "payload": "Additional payload"
}
```

- **Error**

```json
{
  "status": 4xx,
  "message": "Response message from backend"
}