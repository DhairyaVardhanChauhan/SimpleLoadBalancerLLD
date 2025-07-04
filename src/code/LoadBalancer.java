package code;

import code.interfaces.LoadBalancerStrategy;
import code.interfaces.RoundRobin;
import code.interfaces.ServerObserver;

import java.util.List;

public class LoadBalancer implements ServerObserver {
    public static LoadBalancer loadBalancer;
    private List<Server> servers;
    private LoadBalancerStrategy loadBalancerStrategy;

    public LoadBalancer() {
    }

    public LoadBalancer(List<Server> server, LoadBalancerStrategy loadBalancerStrategy) {
        this.servers = server;
        this.loadBalancerStrategy = loadBalancerStrategy;
    }

    public static LoadBalancer initialize(List<Server> servers, LoadBalancerStrategy strategy) {
        if (loadBalancer == null) {
            loadBalancer = new LoadBalancer();
            loadBalancer.setServers(servers);
            loadBalancer.setLoadBalancerStrategy(strategy);
        }
        return loadBalancer;
    }

    public static LoadBalancer getInstance(){
        if(loadBalancer == null){
            loadBalancer = new LoadBalancer();
        }
        return loadBalancer;
    }

    public static LoadBalancer getLoadBalancer() {
        return loadBalancer;
    }

    public static void setLoadBalancer(LoadBalancer loadBalancer) {
        LoadBalancer.loadBalancer = loadBalancer;
    }

    public List<Server> getServers() {
        return servers;
    }

    public void setServers(List<Server> servers) {
        this.servers = servers;
        for(Server server : servers){
            server.addObserver(this);
        }
    }

    public LoadBalancerStrategy getLoadBalancerStrategy() {
        return loadBalancerStrategy;
    }

    public void setLoadBalancerStrategy(LoadBalancerStrategy loadBalancerStrategy) {
        this.loadBalancerStrategy = loadBalancerStrategy;
    }

    public Server getServer(int id,LoadBalancerStrategy loadBalancerStrategy) {
        return loadBalancerStrategy.getServer(id);
    }


    @Override
    public void onServerDown(Server server,Boolean status) {
        System.out.println("LoadBalancer: Server " + server.getServerId() + " is " + (status?"up":"down"));
    }
}
