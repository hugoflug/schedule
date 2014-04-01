clear;

L5 = 200;
L10 = 100;
L15 = 80;
L20 = 40;

X = [L5; L10; L15; L20];
fontsize = 14;
bar(X);
set(gca,'XTickLabel',{'50', '100', '150', '200'});
set(gca,'fontsize', fontsize);
ylabel('Iterations', 'fontsize', fontsize);
xlabel('Number of neighbours', 'fontsize', fontsize);