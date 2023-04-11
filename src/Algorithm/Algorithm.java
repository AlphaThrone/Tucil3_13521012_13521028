package Algorithm;

public abstract class Algorithm {
    private int id;
    private String name;
    private String fullName;
    private String desc;

        // === CONSTRUCTOR =====================================================================
    public Algorithm()
    {
        this.id = -1;
        this.name = "";
        this.fullName = "";
        this.desc = "";
    }

    public Algorithm(String name)
    {
        this.name = name;
        this.fullName = "";
        this.desc = "";

        if (name == "UCS")
        {
            this.id = 0;
            this.fullName = "Uniform Cost Search";
            this.desc = "Uniform-cost search is an uninformed search algorithm that uses the lowest cumulative cost to find a path from the source to the destination. Nodes are expanded, starting from the root, according to the minimum cumulative cost. The uniform-cost search is then implemented using a Priority Queue.";
        }
        else if (name == "AStar")
        {
            this.id = 1;
            this.fullName = "AStar";
            this.desc = "Depth-first search starts a graph's traversal at an arbitrary vertex by marking it as visited. On each iteration, the algorithm proceeds to an unvisited vertex that is adjacent to the on it is currently in.";
        }
    }

    public String getDesc(){
        return this.desc;
    }

    public void setDesc(String desc){
        this.desc = desc;
    }

    public abstract void pathfind();
}
