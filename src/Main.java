import code.LoadBalancer;
import code.Server;
import code.interfaces.IpHashing;
import code.interfaces.RoundRobin;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        int request1 = 1;
        int request2 = 2;
        int request3 = 3;
        int request4 = 4;
        int request5 = 5;
        Server server1 = new Server(1L,true,new ArrayList<>());
        Server server2 = new Server(2L,true,new ArrayList<>());
        LoadBalancer.initialize(List.of(server1,server2), new RoundRobin());
        Server s1  = LoadBalancer.getInstance().getServer(request1,new RoundRobin());
        Server s2  =LoadBalancer.getInstance().getServer(request2,new RoundRobin());
        Server s3 = LoadBalancer.getInstance().getServer(request3,new RoundRobin());
        Server s4 = LoadBalancer.getInstance().getServer(request4,new IpHashing());
        Server s5 = LoadBalancer.getInstance().getServer(request5,new RoundRobin());
        System.out.println(s1.getServerId());
        System.out.println(s2.getServerId());
        System.out.println(s3.getServerId());
        System.out.println(s4.getServerId());
        System.out.println(s5.getServerId());

        // health check

        server1.setServerDown();
        server2.setServerDown();
        server2.setServerUp();
        server1.setServerUp();


    }
}