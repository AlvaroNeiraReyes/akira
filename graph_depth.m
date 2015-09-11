depthxpage=zeros(33,max(a));

j = 0;
acum = 0;
for i = 1:length(a)
    if a(i)==1
        j = j+1;
        acum=0;
    end
    acum = acum+b(i);
    depthxpage(j,a(i)) = acum;
end

%% body binary
types = unique(body);
bodybin = zeros(length(body),length(types));
for i = 1:length(types)
    bodybin(:,i) = strcmp(body,types{i}); 
end

%% graph     
for i = 1:size(depthxpage,1)
    f=figure;
    hold on;
    xlabel('DOM tree level');
    ylabel('Object quantity');
    title(['Object quantity vs depth (page ' num2str(i) ')']);
    bar(depthxpage(i,:));
    hold off;
    saveas(f,['histAcum' num2str(i) '.png']);
end

f=figure;
hold on;
xlabel('DOM tree level');
ylabel('Object quantity');
title('Object quantity vs depth (total)');
bar(sum(depthxpage));
hold off;
saveas(f,'histTotalacum.png');