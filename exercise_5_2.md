## Exercise 5.2

### What is the main difference between @NotNull and @NonNull annotations?

- `@NotNull` (Bean Validation API) is used for **runtime validation** to ensure that a value is not null, often for input validation in DTOs.
- `@NonNull` (e.g., Lombok or IDE annotations) ensures null-safety at **compile time** by helping developers and tools detect potential null pointer issues early.

### Why is it reasonable in this project to use bean validation for the DTOs and a different approach for the domain objects?

- DTOs handle external data and benefit from runtime validation (`@NotNull`) to ensure input integrity before reaching the core logic.
- Domain objects represent the application's core logic, where compile-time null-safety (`@NonNull`) ensures cleaner code and prevents runtime errors without mixing validation logic into the domain model.