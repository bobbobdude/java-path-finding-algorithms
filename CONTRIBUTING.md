# Contributing Guidelines for Group B's Temple of Gloom

##  Commit Messages
We follow the [Conventional Commits](https://www.conventionalcommits.org/en/v1.0.0/) standard. This helps us maintain a clear and organized project history. Please structure your commit messages as follows:

- `feat`: A new feature
- `fix`: A bug fix
- `docs`: Documentation only changes
- `style`: Changes that do not affect the meaning of the code (white-space, formatting, missing semi-colons, etc)
- `refactor`: A code change that neither fixes a bug nor adds a feature
- `test`: Adding missing or correcting existing tests
- `chore`: Changes to the build process or auxiliary tools and libraries such as documentation generation

## Documentation
All public classes and methods must include full Javadoc comments. This ensures that the code is well-documented and easy to understand for all contributors.

## Binary and .class files

Please do not commit any binary files or .class files to the repository. These files should be added to your .gitignore file. Only commit source code and necessary configuration files.

## Pull Requests
To ensure code quality and consistency, each pull request must:

1. Adhere to the above standards.
2. Pass all existing and new tests.
3. Receive at least two approving reviews from project members.
