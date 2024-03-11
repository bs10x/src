


for k = 1, 1000 do
	if k % 3 == 1 then
		a = 1 + math.sqrt(k / 12*k + 1)
		elseif k % 3 == 2 then
		a = 5*k^3 / k^3 + 1
	elseif k % 3 == 0 then
		a = (-1)^k / k
	end
	
	print(a)
end
