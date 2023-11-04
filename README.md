# Go-Survival

Welcome to **Go-Survival**, a 2D interactive multi-level game where you navigate a submarine through perilous waters, swerving away from dangerous enemies and collecting valuable items. Immerse yourself in this underwater adventure filled with sharks, octopuses, and hidden treasures, all brought to life with engaging music and challenging gameplay.

<img width="796" alt="Screenshot 2023-11-04 at 09 59 01" src="https://github.com/Sombrero-J/Go-Survival/assets/108386252/4838c871-5708-442a-94e5-c2825145f2b0">

## Description

In Go-Survival, you pilot a submarine with the mission to avoid or shoot down enemies while collecting various items. Your submarine has health and fuel bars, which can be replenished by collecting mermaids and jellyfish, respectively. Each level presents unique objectives, ranging from crystal collection to enemy annihilation.

### Characters

- **Submarine**: Health 100, Fuel 100
- **Bullet**: Damage 10
- **Shark (Enemy)**: Damage 20, Health 20
- **Octopus (Enemy)**: Damage 15, Health 10
- **Mermaid (Collectible)**: Health Restore 10
- **Jellyfish (Collectible)**: Fuel Restore 5
- **Crystal (Collectible)**: Collect to progress in levels

### Levels

- **Level 1**: One enemy - Octopus. Infinite fuel. Collect crystals & avoid octopus to win.
<img width="792" alt="Screenshot 2023-11-04 at 11 08 16" src="https://github.com/Sombrero-J/Go-Survival/assets/108386252/b53a4540-6ea0-4c25-b9c2-0d46aaa7a9b9">

- **Level 2**: Two enemies – Octopus & Shark. Infinite fuel. Shoot Octopus and collect crystals to win.
<img width="789" alt="Screenshot 2023-11-04 at 11 09 10" src="https://github.com/Sombrero-J/Go-Survival/assets/108386252/61dd3320-2b03-42cc-9af6-760308bc6437">

- **Level 3**: Two enemies – Octopus & Shark. Finite fuel. Shoot enemies and collect crystals to progress.
 <img width="792" alt="Screenshot 2023-11-04 at 11 09 41" src="https://github.com/Sombrero-J/Go-Survival/assets/108386252/789b9716-6e50-4780-b6d0-0efbbc03c916">


## Mechanics

- **Movement**: Use WASD keys.
- **Collecting Items**: Click with the mouse.
- **Shooting**: Aim with the cursor and shoot with the space bar.

### Interface

- Pause and resume gameplay at any time.
- In-game kill feeds
- Save progress feature.
- Adjustable volume settings.
- Level progression (no skipping levels).

## Technical Details

The game leverages Java’s object-oriented programming capabilities, utilising interfaces, abstract classes, inheritance, and more. Key Java features include:

- Interface classes
- Abstract classes
- Inheritance
- Polymorphism
- Encapsulation
- Timer for object spawning

### Physics Engine Features

The game utilises the physics engine provided by my university, incorporating:

- Physical collisions
- Event handling (Key Listeners, Mouse Listeners, Collision Listeners, Action Listeners, Step Listeners)
- Dynamic & Static Bodies
- Ghostly Fixtures
- User View (for painting foreground & background)

## Installation Requirements

To run Go-Survival, ensure you have a modern computer with a keyboard, mouse, and the Java Virtual Machine (JVM) installed. No additional dependencies are required, but a speaker system is recommended for the full experience.

## Installation & Running the Game

1. Clone the repository or download the ZIP file.
2. Navigate to the directory containing the game files.
3. Run the game using the following command: `java -jar Go-Survival.jar`

## Reporting Issues & Contributions

Encountered a bug or have suggestions for improvements? Feel free to open an issue in the repository or submit a pull request with your proposed changes.

## Acknowledgments

Special thanks to my university for providing access to the physics engine and to my professors, Radu and Charlie, for their invaluable guidance.

## License

This project is licensed under the GNU Affero General Public License v3.0 (AGPL-3.0). You are free to download, play, and contribute to the project. However, any sale or commercial use of the original or modified code is strictly prohibited. If you run a modified version on a network server, you must also make the modified source code available under the same license.
