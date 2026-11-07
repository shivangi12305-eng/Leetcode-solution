import java.util.PriorityQueue;

class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {
        int n = servers.length;
        int m = tasks.length;
        int[] ans = new int[m];

        // Free servers heap: sorted by [weight, index]
        PriorityQueue<int[]> freeServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            return Integer.compare(a[1], b[1]);
        });

        // Busy servers heap: sorted by [availableTime, weight, index]
        PriorityQueue<int[]> busyServers = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(a[0], b[0]);
            if (a[1] != b[1]) return Integer.compare(a[1], b[1]);
            return Integer.compare(a[2], b[2]);
        });

        // Initialize free servers
        for (int i = 0; i < n; i++) {
            freeServers.offer(new int[]{servers[i], i});
        }

        int currTime = 0;

        for (int i = 0; i < m; i++) {
            currTime = Math.max(currTime, i);

            // Release all servers that have finished processing by currTime
            while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                int[] server = busyServers.poll();
                freeServers.offer(new int[]{server[1], server[2]});
            }

            // If no free servers are available, jump time to when the earliest server becomes free
            if (freeServers.isEmpty()) {
                currTime = busyServers.peek()[0];
                while (!busyServers.isEmpty() && busyServers.peek()[0] <= currTime) {
                    int[] server = busyServers.poll();
                    freeServers.offer(new int[]{server[1], server[2]});
                }
            }

            // Assign the current task to the best available free server
            int[] bestServer = freeServers.poll();
            ans[i] = bestServer[1];
            busyServers.offer(new int[]{currTime + tasks[i], bestServer[0], bestServer[1]});
        }

        return ans;
    }
}