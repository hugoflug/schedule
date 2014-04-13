clear;

tabu_small_1;
tabu_small_2;
tabu_small_3;
tabu_small_4;
tabu_small_5;
tabu_small_6;
tabu_small_7;
tabu_small_8;
tabu_small_9;
tabu_small_10;
tabu_medium_1;
tabu_medium_2;
tabu_medium_3;
tabu_medium_4;
tabu_medium_5;
tabu_medium_6;
tabu_medium_7;
tabu_medium_8;
tabu_medium_9;
tabu_medium_10;
genetic_small_1;
genetic_small_2;
genetic_small_3;
genetic_small_4;
genetic_small_5;
genetic_small_6;
genetic_small_7;
genetic_small_8;
genetic_small_9;
genetic_small_10;
genetic_medium_1;
genetic_medium_2;
genetic_medium_3;
genetic_medium_4;
genetic_medium_5;
genetic_medium_6;
genetic_medium_7;
genetic_medium_8;
genetic_medium_9;
genetic_medium_10;

ts = [time_ts_1 time_ts_2 time_ts_3 time_ts_4 time_ts_5 time_ts_6 time_ts_7 time_ts_8 time_ts_9 time_ts_10];
tm = [time_tm_1 time_tm_2 time_tm_3 time_tm_4 time_tm_5 time_tm_6 time_tm_7 time_tm_8 time_tm_9 time_tm_10];
gas = [time_gas_1 time_gas_2 time_gas_3 time_gas_4 time_gas_5 time_gas_6 time_gas_7 time_gas_8 time_gas_9 time_gas_10];
gam = [time_gam_1 time_gam_2 time_gam_3 time_gam_4 time_gam_5 time_gam_6 time_gam_7 time_gam_8 time_gam_9 time_gam_10];

tabuSmallTime = mean(ts);
geneticSmallTime = mean(gas);

tabuMediumTime = mean(tm);
geneticMediumTime = mean(gam);

X = [tabuSmallTime geneticSmallTime; tabuMediumTime geneticMediumTime];

bar(X);
l = legend('Tabu Search', 'Genetic Algorithm');
set(l, 'Location', 'NorthWest');
set(gca,'XTickLabel',{'Small', 'Medium'});
set(gca,'fontsize',14);
ylabel('Time   (s)', 'fontsize', 14);