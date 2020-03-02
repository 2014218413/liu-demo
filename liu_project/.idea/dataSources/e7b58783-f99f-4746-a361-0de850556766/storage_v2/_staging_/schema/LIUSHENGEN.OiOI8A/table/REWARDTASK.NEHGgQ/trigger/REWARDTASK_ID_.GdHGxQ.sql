create trigger REWARDTASK_ID_
    before insert
    on REWARDTASK
    for each row
    when (new.id is null)
begin
        select concat('悬赏任务:',RewardTask_Id.nextval) into:new.id from DUAL;
    end;
/

