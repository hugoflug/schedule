clear;
tabuLargeResults;
geneticLargeResults;

fontsize1 = 12;
fontsize2 = 15;
%plot([1:1:size(tabu_avg,2)],tabu_avg,'b',[1:1:size(genetic_avg,2)],genetic_avg,'g');
subplot(2,1,1); plot(tabu_avg);
title('Tabu search','fontsize',fontsize2)
xlabel('Iterations','fontsize',fontsize2);
ylabel('Evaluation score','fontsize',fontsize2);
%legend('Tabu search', 'Genetic algorithm');
subplot(2,1,2); plot(genetic_avg)
title('Genetic algorithm','fontsize',fontsize2)
xlabel('Iterations','fontsize',fontsize1);
ylabel('Evaluation score','fontsize',fontsize1);