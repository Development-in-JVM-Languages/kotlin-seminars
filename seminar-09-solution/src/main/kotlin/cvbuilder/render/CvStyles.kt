package cvbuilder.render

object CvStyles {
    object ClassNames {
        const val CONTAINER = "cv-container"
        const val SECTION = "section"
        const val CONTACT = "contact"
        const val EDUCATION = "education"
        const val INSTITUTION = "institution"
        const val PROJECTS = "projects"
        const val PROJECT = "project"
        const val SKILLS = "skills"
        const val SKILLS_LIST = "skills-list"
        const val SKILL_ITEM = "skill-item"
    }

    const val STYLES = """
        :root {
            --primary-color: #2c3e50;
            --secondary-color: #34495e;
            --accent-color: #3498db;
            --background-color: #ffffff;
            --text-color: #2c3e50;
            --border-color: #ecf0f1;
            --section-spacing: 1rem;
            --border-radius: 4px;
        }
        
        @import url('https://fonts.googleapis.com/css2?family=Playfair+Display:wght@700&family=Source+Sans+Pro:wght@400;600&display=swap');
        
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }
        
        body {
            font-family: 'Source Sans Pro', Georgia, serif;
            line-height: 1.4;
            color: var(--text-color);
            background-color: var(--background-color);
            font-size: 0.85rem;
        }
        
        .${ClassNames.CONTAINER} {
            max-width: 210mm;
            margin: 0 auto;
            padding: 1.5rem;
            background: var(--background-color);
        }
        
        .${ClassNames.CONTACT} {
            text-align: center;
            margin-bottom: var(--section-spacing);
            padding: 1rem;
            background: var(--primary-color);
            border-radius: var(--border-radius);
        }
        
        .${ClassNames.CONTACT} h1 {
            font-family: 'Playfair Display', serif;
            font-size: 1.8rem;
            margin-bottom: 0.5rem;
            letter-spacing: 0.5px;
            color: black;
        }
        
        .${ClassNames.CONTACT} p {
            margin: 0.25rem 0;
            color: black;
            font-size: 0.9rem;
        }
        
        .${ClassNames.CONTACT} a {
            color: black;
            text-decoration: none;
            border-bottom: 1px dotted rgba(255, 255, 255, 0.7);
        }
        
        .${ClassNames.SECTION} {
            margin-bottom: var(--section-spacing);
            padding: 1rem;
            background: #f8f9fa;
            border-radius: var(--border-radius);
        }
        
        h2 {
            font-family: 'Playfair Display', serif;
            color: var(--primary-color);
            font-size: 1.2rem;
            margin-bottom: 0.75rem;
            padding-bottom: 0.25rem;
            border-bottom: 2px solid var(--accent-color);
        }
        
        .${ClassNames.INSTITUTION} {
            margin-bottom: 0.75rem;
            padding: 0.5rem;
            background: white;
            border-radius: var(--border-radius);
        }
        
        .${ClassNames.INSTITUTION} h3 {
            color: var(--secondary-color);
            font-size: 1rem;
            margin-bottom: 0.25rem;
            font-weight: 600;
        }
        
        .${ClassNames.PROJECT} {
            margin-bottom: 0.75rem;
            padding: 0.5rem;
            background: white;
            border-radius: var(--border-radius);
        }
        
        .${ClassNames.PROJECT} h3 {
            color: var(--secondary-color);
            font-size: 1rem;
            margin-bottom: 0.25rem;
            font-weight: 600;
        }
        
        .${ClassNames.PROJECT} p {
            margin-bottom: 0.25rem;
            color: var(--text-color);
        }
        
        .${ClassNames.SKILLS_LIST} {
            display: flex;
            flex-wrap: wrap;
            gap: 0.5rem;
            margin-top: 0.5rem;
        }
        
        .${ClassNames.SKILL_ITEM} {
            background: var(--accent-color);
            color: white;
            padding: 0.25rem 0.75rem;
            border-radius: 15px;
            font-size: 0.8rem;
            letter-spacing: 0.5px;
            font-weight: 600;
        }
        
        ul {
            list-style-type: none;
            margin-left: 0.75rem;
        }
        
        li {
            position: relative;
            padding-left: 0.75rem;
            color: var(--text-color);
        }
        
        li:before {
            content: "•";
            position: absolute;
            left: -0.75rem;
            color: var(--accent-color);
        }
        
        @media print {
            @page {
                size: A4;
                margin: 1cm;
            }
            
            body {
                -webkit-print-color-adjust: exact !important;
                print-color-adjust: exact !important;
            }
            
            .${ClassNames.CONTAINER} {
                margin: 0;
                padding: 0.75rem;
                width: 100%;
            }
            
            .${ClassNames.SECTION} {
                page-break-inside: avoid;
                margin-bottom: 0.75rem;
            }
            
            .${ClassNames.CONTACT} {
                background: var(--primary-color) !important;
            }
            
            .${ClassNames.CONTACT} h1,
            .${ClassNames.CONTACT} p,
            .${ClassNames.CONTACT} a {
                color: black !important;
            }
            
            .${ClassNames.SKILL_ITEM} {
                background: var(--accent-color) !important;
                color: black !important;
            }
            
            .${ClassNames.PROJECT}, .${ClassNames.INSTITUTION} {
                border: 1px solid var(--border-color);
            }
        }
    """
}