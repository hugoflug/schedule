clear;

L10 = 7.2344;
L60 = 16.221;
L500 = 60.3093;

X = [L10; L60; L500];
fontsize = 14;
bar(X, 0.4);
set(gca,'XTickLabel',{'10', '60', '500'});
set(gca,'fontsize', fontsize);
ylabel('Time   (s)', 'fontsize', fontsize);
xlabel('Size of population', 'fontsize', fontsize);