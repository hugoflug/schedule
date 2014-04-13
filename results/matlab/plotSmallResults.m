clear;
tabuTestResults;
geneticTestResults;

fontsize1 = 12;
fontsize2 = 15;
plot(Tabu(:,1),Tabu(:,2),'b',Genetic(:,1),Genetic(:,2),'g');
%subplot(2,1,1); plot(Tabu(:,1),Tabu(:,2));
%title('Tabu search','fontsize',fontsize2)
xlabel('Iterations','fontsize',fontsize2);
ylabel('Evaluation score','fontsize',fontsize2);
legend('Tabu search', 'Genetic algorithm');
%subplot(2,1,2); plot(Genetic(:,1),Genetic(:,2))
%title('Genetic algorithm','fontsize',fontsize2)
%xlabel('Iterations','fontsize',fontsize1);
%ylabel('Evaluation score','fontsize',fontsize1);