# ArkidilloEngine
Homemade 2d Java based game engine

## Installation
1. Install JDK
2. Place user code in `ArkidilloEngine/src/userCode`

## Usage

### Getting Started: Create + display sprite
Create a `Scene` object.  
Create a `Sprite` object, by passing the object its location, an image file, and the `Scene` object.

### Game logic
`Scene` and `Sprite` each have their own trigger functions that can help you program your game logic.  
  
`Scene`:   
    - `update()`: Called each frame  
    - `onCreate()`: Called on creation of the `Scene`  
    - `keyPressed/keyReleased/keyTyed(int keyCode)`: Triggered for each of these key events  
    - `onCollision(Sprite sprite1, Sprite sprite2)`: Called when 2 collideable sprites collide  
  
`Sprite`:   
    - `update()`: Called each frame  
    - `keyPressed/keyReleased/keyTyed(int keyCode)`: Triggered for each of these key events  
    - `setCollideable(boolean collideable)`: Tell the sprite whether it should be checked for collisions  
    - `onCollision(Sprite sprite1)`: Called when 2 collideable sprites collide  
    - Physics API: Various functions that can offload movement calculations to the engine rather than needing to be coded  
    - Animation API: Various functions that allow you to setup and advance through animation frames
    
