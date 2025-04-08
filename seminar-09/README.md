# CV Builder DSL

## Overview
Your task is to implement a Domain-Specific Language (DSL) in Kotlin for creating CVs/Resumes. The rendering logic and data models are already provided - you only need to focus on creating a user-friendly DSL interface.

## Task Description
Create a DSL that allows writing CV content in the following style:

```kotlin
val myCV = cv {
    contact {
        name = "John Doe"
        email = "john.doe@example.com"
        phoneNumber = "+1234567890"
    }
    education {
        "Degree Name" at "University Name" from "2020" to "2024"
    }
    projects {
        project {
            name = "Project Name"
            description = "Project Description"
            year = "2024"
            -"Technology1"
            -"Technology2"
        }
    }
    skills {
        -"Skill1"
        -"Skill2"
    }
}
```

## Requirements
- Implement the DSL structure to support all sections (contact, education, projects, skills)
- Use Kotlin's DSL features to make the syntax clean and intuitive
- The DSL should generate a `CV.kt` data class.

## Getting Started
1. Study the provided models to understand the structure
2. Implement the DSL builders
3. Test your implementation using the provided renderer

## Hint
Focus on understanding Kotlin's DSL features like:
- Function literals with receiver
- Scope functions
- Extension functions