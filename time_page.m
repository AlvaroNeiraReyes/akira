for j = 1:33
%     ObjectTime = [(1:359)' zeros(359,3)];
%     for i =1:length(intervals)
%         index = find(intervals{i}(:,1)==j);
%         aux = intervals{i}(index,:);
%         for k = 1:size(aux,1)
%             ObjectTime(aux(k,3),2) = ObjectTime(aux(k,3),2) + (aux(k,5) - aux(k,4))*2;
%             ObjectTime(aux(k,3),3) = ObjectTime(aux(k,3),3) + (aux(k,5) - aux(k,4))*2;
%             ObjectTime(aux(k,3),4) = ObjectTime(aux(k,3),4)+1;
%         end
%     end
%     ObjectTime(:,3) = ObjectTime(:,3)./ObjectTime(:,4);
%     ObjectsTime1 = [];
%     for i=1:length(objectsID{j})
%         aux = find(ObjectTime(:,1)==objectsID{j}(i,1));
%         ObjectsTime1 = [ObjectsTime1; ObjectTime(aux,:)];
%     end

    TXT1 = [objectsXY{j} objectsWH{j} objectsID{j}];
    csvwrite(['ObjectsPage' num2str(j) '.txt'],TXT1);
    
%     TXT1 = [objectsXY{j} objectsWH{j} ObjectsTime1];
%     csvwrite(['ObjectsTimeClusterPage' num2str(j) '.txt'],TXT1);
% 
%     TXT1sort = sortrows(TXT1,-6);
%     TXT1sort = [TXT1sort (1:size(TXT1,1))'];
%     csvwrite(['ObjectsTimeClusterPageSort' num2str(j) '.txt'],TXT1sort);
end

clear i j k aux index