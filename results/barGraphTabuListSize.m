clear;
V10 = [12.881 10.454 10.153 8.194 9.606 11.237 9.704 10.279 11.673 10.137];
V100 = [8.695 13.963 12.595 10.147 16.285 11.793 13.082 10.489 8.168 14.915];
V1000 = [11.83 13.791 11.488 15.818 10.705 11.918 11.37 12.713 14.227 11.894];

A10 = mean(V10);
A100 = mean(V100);
A1000 = mean(V1000);

X = [A10; A100; A1000];
fontsize = 14;
bar(X, 0.4);
set(gca,'XTickLabel',{'10', '100', '1000'});
set(gca,'fontsize', fontsize);
ylabel('Time (s)', 'fontsize', fontsize);
xlabel('Tabu list size', 'fontsize', fontsize);