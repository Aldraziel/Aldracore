## How to use API[![](pin.svg)](#api)

---

- First, get the API instance with :
```java
final AldraCoreAPI api = AldraCoreAPI.get();
```
- Then, you can get and use the managers with :
```java
final IAldraEventManager event = api.getEventManager();
final IAldraPlayerManager pm = api.getPlayerManager();
final IAldraCacheManager cache = api.getCacheManager();
````
- Be careful ! If you want to update player stats without using the IAldraPlayerManager, you need to call the :
```java
AldraCoreAPI.get().getEventManager().callEvent(new PlayerStatsUpdateEvent(player));
```
to stay the IAldraCacheManager up to date.

- You can refer to the Javadoc in AldraCoreAPI and his managers to know how to use them.
