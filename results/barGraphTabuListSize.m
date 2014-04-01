clear;

L5 = 200;
L10 = 100;
L15 = 80;
L20 = 40;

X = [L5; L10; L15; L20];
fontsize = 14;
bar(X);
set(gca,'XTickLabel',{'5', '10', '15', '20'});
set(gca,'fontsize', fontsize);
ylabel('Iterations', 'fontsize', fontsize);
xlabel('Tabu list size', 'fontsize', fontsize);