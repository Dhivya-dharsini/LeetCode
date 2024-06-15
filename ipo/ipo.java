class Solution {
    public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
        // Priority queue to store projects by their capital requirements in ascending order.
        PriorityQueue<int[]> minC = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        // Priority queue to store available projects by their profit in descending order.
        PriorityQueue<Integer> maxP = new PriorityQueue<>((a, b) -> b - a);

        // Populate the minC queue with all projects, sorted by their capital requirements.
        for (int i = 0; i < profits.length; i++) {
            minC.offer(new int[]{capital[i], profits[i]});
        }

        // We can perform at most k projects.
        while (k-- > 0) {
            // Move all projects that can be started with the current capital to the maxP queue.
            while (!minC.isEmpty() && minC.peek()[0] <= w) {
                maxP.add(minC.poll()[1]);
            }

            // If there are no projects that can be started, break the loop.
            if (maxP.isEmpty()) {
                break;
            }

            // Start the project with the highest profit available.
            w += maxP.poll();
        }

        // Return the final maximized capital after completing up to k projects.
        return w;
    }
}

/*
Explanation:
Priority Queues Initialization:

minC is a priority queue (min-heap) that stores projects sorted by their capital requirements. The projects with the least capital requirement will be at the front.
maxP is a priority queue (max-heap) that stores available projects sorted by their profits in descending order. The projects with the highest profit will be at the front.
Populating minC Queue:

We iterate through the given projects and push each project's capital requirement and profit into minC.
Processing Projects:

We have a loop that runs up to k times, meaning we can complete at most k projects.
Inside this loop, we first move all projects that can be started with the current capital w from minC to maxP. This ensures that maxP always contains projects that we can afford to start.
If maxP is empty after this step, it means there are no projects we can start with the current capital, so we break out of the loop.
Otherwise, we take the project with the highest profit from maxP (using poll()) and add its profit to our current capital w.
Returning the Result:

After completing up to k projects or breaking out of the loop (if no more projects can be started), we return the final capital w.
*/