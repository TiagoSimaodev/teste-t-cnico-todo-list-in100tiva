# Upgrade Plan: to-do-list-100tiva (20260511200919)

- **Generated**: May 11, 2026, 20:09:19
- **HEAD Branch**: N/A
- **HEAD Commit ID**: N/A

## Available Tools

**JDKs**
- JDK 17.0.12: C:\Program Files\Java\jdk-17\bin (current project JDK, used by step 2)
- JDK 21.0.11: C:\Program Files\Java\jdk-21.0.11\bin (target JDK, used by steps 3+)

**Build Tools**
- Maven 3.9.15: C:\apache-maven-3.9.15\bin (compatible with Java 21)

## Guidelines

> Note: You can add any specific guidelines or constraints for the upgrade process here if needed, bullet points are preferred.

## Options

- Working branch: appmod/java-upgrade-20260511200919
- Run tests before and after the upgrade: true

## Upgrade Goals

- Java: 17 → **21** (latest LTS)

## Technology Stack

| Technology/Dependency    | Current | Min Compatible | Why Incompatible                               |
| ------------------------ | ------- | -------------- | ---------------------------------------------- |
| Java                     | 17      | 21             | User requested latest LTS                      |
| Spring Boot              | 3.2.0   | 3.2.0          | Already compatible with Java 21                |
| Spring Framework         | 6.1.x   | 6.1.x          | Inherited from Spring Boot 3.2.0 (compatible) |
| H2 Database              | 2.x     | 2.x            | Compatible with Java 21                        |
| Hibernate                | 6.x     | 6.x            | Inherited via Spring Boot Data JPA (compatible)|
| springdoc-openapi        | 2.3.0   | 2.3.0          | Compatible with Java 21                        |
| Maven                    | 3.9.15  | 3.9.0          | Minimum version for Java 21 support            |

## Derived Upgrades

- **Maven 3.9.15** (if not already set): Ensures compatibility with Java 21 compilation

## Upgrade Steps

- Step 1: Setup Environment
  - **Rationale**: Verify that JDK 21 is available on the system and prepare the build environment for the upgrade
  - **Changes to Make**: 
    - Verify JDK 21.0.11 is installed and accessible
    - Confirm Maven 3.9.15+ is available
  - **Verification**: `java -version` (should show Java 21), `mvn -version` (should show Maven 3.9.15+)

- Step 2: Setup Baseline
  - **Rationale**: Establish baseline test results with current Java 17 to measure upgrade success
  - **Changes to Make**: 
    - Run full compile and test suite with Java 17
    - Document baseline test pass rate
    - This establishes the acceptance criteria for the upgrade
  - **Verification**: `mvn clean test-compile -q` and `mvn clean test -q` with JAVA_HOME pointing to JDK 17.0.12, Expected Result: All tests pass (baseline)

- Step 3: Update Java Version in pom.xml
  - **Rationale**: Update the target Java version property to Java 21 and verify compilation with the new JDK
  - **Changes to Make**: 
    - Update `<java.version>17</java.version>` to `<java.version>21</java.version>` in pom.xml
    - Run compilation to verify no source-level incompatibilities exist
  - **Verification**: `mvn clean test-compile -q` with JAVA_HOME pointing to JDK 21.0.11, Expected Result: Compilation succeeds (main + test code)

- Step 4: Final Validation
  - **Rationale**: Verify all upgrade goals met, all code compiles cleanly, and all tests pass with Java 21
  - **Changes to Make**: 
    - Resolve any remaining test failures through debugging and fixes
    - Ensure no TODOs or temporary workarounds remain
    - Final clean build with Java 21
  - **Verification**: `mvn clean test-compile -q` and `mvn clean test -q` with JAVA_HOME pointing to JDK 21.0.11, Expected Result: 100% test pass rate (≥ baseline), all tests green

## Key Challenges

None identified. This is a straightforward Java LTS upgrade within the same major version of Spring Boot. Spring Boot 3.2.0 fully supports Java 21, and no API breaking changes are expected.

---

**Note**: This project is not version-controlled (no git repository detected). All changes will remain in the working directory as uncommitted files. Consider initializing git if you plan to track upgrade history.
