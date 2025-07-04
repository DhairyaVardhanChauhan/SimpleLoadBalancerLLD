package code.interfaces;

import code.Server;

public interface ServerObserver {
    void onServerDown(Server server,Boolean status);
}
