---
name: documentation-writer
description: Specialized agent for creating and improving project documentation
tools: ['read', 'agent', 'edit', 'search']
---

# Documentation Writer Subagent

You are a technical writer who creates clear, concise documentation.

## Your Responsibilities
- Receive code review findings from Code Reviewer
- Create/update README.md, CHANGELOG.md, or code comments
- Follow markdown best practices
- create the md file in /docs folder
- Return documentation draft to Code Reviewer for approval

## Output Format
Always return:
1. What you documented
2. Files created/modified
3. Suggested next steps

## Tools
- file operations (create/edit markdown files)
```

### **Demo Flow**

**User prompt**: 
```
"Please review the Java code in src/ and update the documentation with any important findings."
```

**Expected Interaction**:
1. **Code Reviewer** activates and analyzes the Java code
2. **Code Reviewer** finds issues (e.g., missing error handling, security concerns)
3. **Code Reviewer** hands off to **Documentation Writer** with summary
4. **Documentation Writer** creates/updates documentation
5. **Documentation Writer** returns to **Code Reviewer** with draft
6. **Code Reviewer** reviews and either approves or requests changes
7. Final documentation is saved

### **Alternative Simpler Demo: "File Analyzer"**

If you want something even simpler for a quick demo:

**Main Agent: "File Inspector"**
- Scans the project structure
- Identifies specific file types (Java, XML, etc.)
- Hands off to appropriate specialist

**Subagent: "Maven Analyzer"**
- Receives pom.xml from File Inspector
- Analyzes dependencies
- Checks for outdated packages
- Reports back to File Inspector

### **Sample Conversation**
```
You: "Can you analyze my project structure and check if my Maven dependencies are up to date?"

Agent (File Inspector): "I'll scan your project. Let me hand off the Maven analysis to my specialist..."

[Handoff to Maven Analyzer]

Subagent (Maven Analyzer): "I've analyzed pom.xml. Found 3 dependencies with newer versions available. Here's my report..."

[Returns to File Inspector]

Agent (File Inspector): "Based on the Maven Analyzer's findings, here's a complete project assessment..."