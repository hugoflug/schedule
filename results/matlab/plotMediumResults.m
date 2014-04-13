clear;
tabuMediumTestResults;
geneticMediumTestResults;

fontsize1 = 12;
fontsize2 = 15;
plot(Tabu(1:1097,1),Tabu(1:1097,2),'b',Genetic(1:2000,1),Genetic(1:2000,2),'g');
%subplot(2,1,1); plot(Tabu(:,1),Tabu(:,2));
%title('Tabu search','fontsize',fontsize2)
xlabel('Iterations','fontsize',fontsize2);
ylabel('Evaluation score','fontsize',fontsize2);
legend('Tabu search', 'Genetic algorithm');
%subplot(2,1,2); plot(Genetic(:,1),Genetic(:,2))
%title('Genetic algorithm','fontsize',fontsize2)
%xlabel('Iterations','fontsize',fontsize1);
%ylabel('Evaluation score','fontsize',fontsize1);