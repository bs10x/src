
M = {}

function max_matching(G) 

	repeat
		--> search augmenting path P (with bfs on bipartite graph)
		 P = {}
		if true --[[ no such path exists --]] then return M
		else M = M + P end --> M = M xor P
		
end



function bfs(G) 
	L = { ['0'] = {} } --> L.0 = uncovered edges in A
	--> mark edges in L.0 as visited

	for i = 0, n 
		if i % 2 == 1 then
			-- blabla
		else
			-- blabla 2
		end
	end
		
	-- mark edges in L.i as visited 	
