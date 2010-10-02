import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

/**
 * Server.java - Interface to server stuff
 * @author James
 */
public class Server {

    private MinecraftServer server;

    /**
     * Creates a server
     * @param server
     */
    public Server(MinecraftServer server) {
        this.server = server;
    }

    /**
     * Uses the specified console command
     * @param command
     */
    public void useConsoleCommand(String command) {
        server.a(command, server);
    }

    /**
     * Uses the specified console command
     * @param command command to use
     * @param player player to use command as
     */
    public void useConsoleCommand(String command, Player player) {
        server.a(command, player.getUser().a);
    }

    /**
     * Starts a timer using the built-in timer system.
     * @param uniqueString must be unique identifier for this timer
     * @param time time till it expires (6000 roughly equals 5 minutes)
     */
    public void setTimer(String uniqueString, int time) {
        MinecraftServer.b.put(uniqueString, time);
    }

    /**
     * Check to see if your timer has expired yet.
     * @param uniqueString unique identifier
     * @return true if timer has expired
     */
    public boolean isTimerExpired(String uniqueString) {
        return MinecraftServer.b.containsKey(uniqueString);
    }

    /**
     * Returns current server time (0-24000)
     * @return time server time
     */
    public long getTime() {
        return server.e.c;
    }

    /**
     * Sets the current server time
     * @param time time (0-24000)
     */
    public void setTime(long time) {
        server.e.c = time;
    }

    /**
     * Returns the actual Minecraft Server
     * @return
     */
    public MinecraftServer getMCServer() {
        return server;
    }

    /**
     * Tries to match a character's name.
     * @param name
     * @return
     */
    public Player matchPlayer(String name) {
        ea player = null;
        boolean found = false;
        if (("`" + server.f.c().toUpperCase() + "`").split(name.toUpperCase()).length == 2) {
            for (int i = 0; i < server.f.b.size() && !found; ++i) {
                ea localea = (ea) server.f.b.get(i);
                if (("`" + localea.aq.toUpperCase() + "`").split(name.toUpperCase()).length == 2) {
                    player = localea;
                    found = true;
                }
            }
        } else if (("`" + server.f.c() + "`").split(name).length > 2) {
            // Too many partial matches.
            for (int i = 0; i < server.f.b.size() && !found; ++i) {
                ea localea = (ea) server.f.b.get(i);
                if (localea.aq.equalsIgnoreCase(name)) {
                    player = localea;
                    found = true;
                }
            }
        }
        return player != null ? player.getPlayer() : null;
    }

    /**
     * Returns specified player
     * @param name
     * @return
     */
    public Player getPlayer(String name) {
        ea user = server.f.h(name);
        return user == null ? null : user.getPlayer();
    }

    /**
     * Returns the player list
     * @return
     */
    public List<Player> getPlayerList() {
        List<Player> toRet = new ArrayList<Player>();
        for (Object o : server.f.b)
            toRet.add(((ea)o).getPlayer());
        return toRet;
    }

    /**
     * Sets the block
     * @param block
     */
    public void setBlock(Block block) {
        setBlockAt(block.getX(), block.getY(), block.getZ(), block.getType());
    }

    /**
     * Returns the block at the specified location
     * @param x
     * @param y
     * @param z
     * @return block
     */
    public Block getBlockAt(int x, int y, int z) {
        return new Block(getBlockIdAt(x, y, z), x, y, z);
    }

    /**
     * Sets the block type at the specified location
     * @param blockType
     * @param x
     * @param y
     * @param z
     */
    public void setBlockAt(int blockType, int x, int y, int z) {
        server.e.d(x, y, z, blockType);
    }

    /**
     * Returns the highest block Y
     * @param x
     * @param z
     * @return highest block altitude
     */
    public int getHighestBlockY(int x, int z) {
        return server.e.d(x, z);
    }

    /**
     * Returns the block type at the specified location
     * @param x
     * @param y
     * @param z
     * @return block type
     */
    public int getBlockIdAt(int x, int y, int z) {
        return server.e.a(x, y, z);
    }
}
