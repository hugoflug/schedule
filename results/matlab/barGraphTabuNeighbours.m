clear;

L5 = 1.557;
L10 = 2.0368;
L100 = 12.2263;
L500 = 51.3696;

X = [L5; L10; L100; L500];
fontsize = 14;
bar(X, 0.4);
set(gca,'XTickLabel',{'5', '10', '100', '500'});
set(gca,'fontsize', fontsize);
ylabel('Time   (s)', 'fontsize', fontsize);
xlabel('Number of neighbors', 'fontsize', fontsize);