# Go-Survival

Welcome to **Go-Survival**, a 2D interactive multi-level game where you navigate a submarine through perilous waters, swerving away from dangerous enemies and collecting valuable items. Immerse yourself in this underwater adventure filled with sharks, octopuses, and hidden treasures, all brought to life with engaging music and challenging gameplay.

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

- **Level 1**: No enemies. Infinite fuel. Collect crystals to win.
- **Level 2**: One enemy – Octopus. Infinite fuel. Shoot Octopus and collect crystals to win.
- **Level 3**: Two enemies – Octopus & Shark. Finite fuel. Shoot enemies and collect crystals to progress.

## Mechanics

- **Movement**: Use WASD keys.
- **Collecting Items**: Click with the mouse.
- **Shooting**: Aim with the cursor and shoot with the space bar.

### Interface

- Pause and resume gameplay at any time.
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
