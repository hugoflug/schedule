clear;

tabuSmallTime = 10.465;
geneticSmallTime = 13.183;

tabuMediumTime = 72.285;
geneticMediumTime = 414.584;

X = [tabuSmallTime geneticSmallTime; tabuMediumTime geneticMediumTime];

bar(X);
l = legend('Tabu Search', 'Genetic Algorithm');
set(l, 'Location', 'NorthWest');
set(gca,'XTickLabel',{'Small', 'Medium'});
set(gca,'fontsize',14);
ylabel('Time (s)', 'fontsize', 14);