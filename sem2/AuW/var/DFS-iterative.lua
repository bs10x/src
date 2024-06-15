-- Function to perform iterative DFS
function iterativeDFS(graph, startNode)
    -- Create an empty stack and push the start node
    local stack = {startNode}
    
    -- Create a set to keep track of visited nodes
    local visited = {}
    
    -- Iterate until the stack is empty
    while #stack > 0 do
        -- Pop the top node from the stack
        local currentNode = table.remove(stack)
        
        -- Check if the current node has not been visited
        if not visited[currentNode] then
            -- Mark the current node as visited
            visited[currentNode] = true
            
            -- Process the current node (e.g., print its value)
            print("Visiting node:", currentNode)
            
            -- Get the neighbors of the current node
            local neighbors = graph[currentNode]
            
            -- Push the unvisited neighbors onto the stack
            for i = 1, #neighbors do
                local neighbor = neighbors[i]
                if not visited[neighbor] then
                    table.insert(stack, neighbor)
                end
            end
        end
    end
end

-- Example usage
local graph = {
    A = {"B", "C"},
    B = {"D", "E"},
    C = {"F"},
    D = {},
    E = {"F"},
    F = {}
}

--[[

 A
 | \
 B  C 
 | \  \ 
 D  E – F

--]]

iterativeDFS(graph, "A")
