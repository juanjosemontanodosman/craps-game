# AGENTS.md

## Repo facts
- JavaFX 21.0.6 desktop game built with Maven; one Java module. No CI, lint, formatter, or tests yet.
- Three names look inconsistent on purpose:
  - Maven artifact: `craps-game-26-2`
  - Java module: `com.example.crapsgame262`
  - Base package / resources: `com.example.crapsgame262`
- User-facing text is Spanish; keep it Spanish.

## Commands
- Compile: `mvn -q -DskipTests compile`
- Run: `mvn clean javafx:run` (the `default-cli` execution in `pom.xml` launches
  `com.example.crapsgame262/com.example.crapsgame262.HelloApplication` on the module path).
- Test: `mvn test` currently finds no tests. `src/test` does not exist despite JUnit 5 deps.
  If adding Jupiter tests, also add `maven-surefire-plugin` 3.x; Maven 3.8.x defaults to Surefire 2.12.4,
  which does not discover JUnit 5 tests.
- `mvnw` / `mvnw.cmd` are checked in but `.mvn/wrapper/` is missing, so the wrapper cannot start.
  Use an installed `mvn`, or restore `.mvn/wrapper/maven-wrapper.properties` first.
- Set `JAVA_HOME` before building (JDK 17+; this checkout's IDE uses JDK 27).
  `pom.xml` pins source/target 9 while JavaFX 21 needs JDK 17+ at runtime.

## Entrypoints and flow
- `Launcher` is a plain `main` delegating to `Application.launch(HelloApplication.class, ...)`.
  Maven does not use it, but keep it for non-modular launches.
- `HelloApplication.start()` ignores its `Stage` parameter and shows `WelcomeView.getInstance()`.
- Flow: `WelcomeController` reads the nickname, creates a `Player`, injects it via
  `GameView.getInstance().getController().setCurrentPlayer(...)`, shows the game, hides welcome.
- `WelcomeView` / `GameView` are `Stage` subclasses with nested-holder singletons, not controllers.
- FXML files live under `src/main/resources/com/example/crapsgame262/` and are loaded with
  absolute paths such as `/com/example/crapsgame262/game-view.fxml`.
- `GameController` currently only rolls two dice and replaces images. Craps scoring/point state is
  not implemented; the `Tiro:` / `Punto:` labels are placeholders.
- `ArithmeticOperations` is an unused Javadoc/demo class, not part of the game.

## FXML / JavaFX gotchas
- Buttons use `onMouseClicked="#handler"`, not `onAction`; handlers take `(MouseEvent event)`.
- A new controller package must be opened to `javafx.fxml` in `src/main/java/module-info.java`.
  The current FXML controller package is `com.example.crapsgame262.controllers`.
- `Dice.getImagePath()` is only valid after `roll()`; it resolves
  `/com/example/crapsgame262/images/dices/dice<N>.png` (there is no `dice0.png`).
- Keep `fx:controller`, `fx:id`, and the private `@FXML` fields in sync.
- Build output goes to `target/`, which is not in `.gitignore` (only `.idea/` is). Do not commit it.
