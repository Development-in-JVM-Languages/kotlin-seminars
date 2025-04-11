package cvbuilder

import cvbuilder.dsl.CVBuilder.Companion.cv
import cvbuilder.render.HtmlRenderer.Companion.saveToHtml

fun main() {
    val myCV = cv {
        contact {
            name = "Max Mustermann"
            email = "mmax@cub.uni"
            phoneNumber = "+49 123213125"
        }
        education {
            "BSc Software, Data and Technology" at "Constructor University Bremen" from "2023" to "2026"
            "High School Diploma" at "Hermann-Böse-Gymnasium" from "2019" to "2025"
        }
        projects {
            project {
                name = "CV Builder DSL"
                description = "A Kotlin DSL for building CV/Resume in a type-safe way"
                year = "2024"
                -"Kotlin"
                -"DSL"
                url = "https://github.com/username/cv-builder"
            }
            project {
                name = "Machine Learning Pipeline"
                description = "Automated ML pipeline for data processing"
                year = "2023"
                -"Python"
                -"TensorFlow"
                -"Docker"
            }
        }
        skills {
            -"Kotlin"
            -"Python"
            -"ML"
        }

    }
    myCV.saveToHtml("cv.html")
}
