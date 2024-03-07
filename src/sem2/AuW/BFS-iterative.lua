-- Define the BFS function
function bfs(graph, start)
    -- Create a queue and enqueue the start node
    local queue = {start}
    
    -- Create a set to keep track of visited nodes
    local visited = {}
    
    -- Mark the start node as visited
    visited[start] = true
    
    -- Process nodes in the queue until it's empty
    while #queue > 0 do
        -- Dequeue a node from the front of the queue
        local node = table.remove(queue, 1) -- FIXME: this doesn't pop the node
        
        -- Process the current node (e.g., print its value)
        print("Visiting node:", currentNode)
        
        -- Get the neighbors of the current node
        local neighbors = graph[node]
        
        -- Visit each neighbor that hasn't been visited yet
        for _, neighbor in ipairs(neighbors) do
            if not visited[neighbor] then
                -- Mark the neighbor as visited
                visited[neighbor] = true
                
                -- Enqueue the neighbor
                table.insert(queue, neighbor)
            end
        end
    end
end

-- Example usage
local graph = {
    A = {"B", "C"},
    B = {"A", "D"},
    C = {"A", "E"},
    D = {"B"},
    E = {"C"}
}

bfs(graph, "A")
