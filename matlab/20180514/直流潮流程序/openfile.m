function openfile()
global Bus Load Generator Line ;

% 数据文件的打开  选择数据文件 Text 即可
[dfile,pathname]=uigetfile('*.m','Select Data File');
if pathname == 0
    error(' you must select a valid data file')
else
    lfile =length(dfile);
    % strip off .m
    eval(dfile(1:lfile-2));
end