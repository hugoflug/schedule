clear;
tabuTestResults;
geneticTestResults;

fontsize1 = 12;
fontsize2 = 16;
%plot(Tabu(:,1),Tabu(:,2),'b',Genetic(:,1),Genetic(:,2),'g');
subplot(2,1,1); plot(Tabu(:,1),Tabu(:,2));
title('Tabu search','fontsize',fontsize2)
xlabel('Iterations','fontsize',fontsize1);
ylabel('Evaluation score','fontsize',fontsize1);
subplot(2,1,2); plot(Genetic(:,1),Genetic(:,2))
title('Genetic algorithm','fontsize',fontsize2)
xlabel('Iterations','fontsize',fontsize1);
ylabel('Evaluation score','fontsize',fontsize1);