package code;

import code.interfaces.ServerObserver;

import javax.security.auth.Subject;
import java.util.List;

public class Server {
    private Long serverId;
    private boolean serverHealth;
    List<ServerObserver> serverObserverList;

    public Long getServerId() {
        return serverId;
    }

    public Server(Long serverId, boolean serverHealth, List<ServerObserver> serverObserverList) {
        this.serverId = serverId;
        this.serverHealth = serverHealth;
        this.serverObserverList = serverObserverList;
    }

    public Server(Long serverId, boolean serverHealth) {
        this.serverId = serverId;
        this.serverHealth = serverHealth;
    }

    public void setServerId(Long serverId) {
        this.serverId = serverId;
    }

    public boolean isServerHealth() {
        return serverHealth;
    }

    public void setServerHealth(boolean serverHealth) {
        this.serverHealth = serverHealth;
    }

    public void addObserver(ServerObserver serverObserver){
        serverObserverList.add(serverObserver);
    }

    public void removeObserver(ServerObserver serverObserver){
        serverObserverList.remove(serverObserver);
    }
    public void setServerDown(){
        this.serverHealth = false;
        notifyAllObservers(false);
    }
    public void setServerUp(){
        this.serverHealth = true;
        notifyAllObservers(true);
    }

    public void notifyAllObservers(Boolean status){
        for(ServerObserver serverObserver : serverObserverList){
            serverObserver.onServerDown(this,status);
        }
    }

}
