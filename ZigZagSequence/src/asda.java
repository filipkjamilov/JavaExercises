    def buildtree(rows,scoref=entropy):
        if len(rows)==0:
            return decisionnode()
        current_score=scoref(rows)

        # promenlivi so koi sledime koj kriterium e najdobar
        best_gain=0.0
        best_criteria=None
        best_sets=None

        column_count=len(rows[0])-1
        for col in range(0,column_count): # za sekoja kolona (col se dvizi vo intervalot 0 do column_count-1)
        # Sledniov ciklus e za generiranje na recnik od razlicni vrednosti vo ovaa kolona
            column_values={}
            for row in rows:
                column_values[row[col]]=1
            # za sekoja redica se zema vrednosta vo ovaa kolona i se postavuva kako kluc vo column_values
            # za sekoja razlicna vrednost na ovaa kolona se pravi podelba na mnozestvoto
            for value in column_values.keys():
                (set1,set2)=divideset(rows,col,value)
            # za konkretnata podelba se presmetuva informaciona dobivka (gain)
                p=float(len(set1))/len(rows) # verojatnost da se pogodi tekovnata vrednost na ovoj atribut
                gain=current_score-p*scoref(set1)-(1-p)*scoref(set2)
                if gain>best_gain and len(set1)>0 and len(set2)>0:
            # ovaa e podelba e najadobra do sega pa ke ja zacuvame
                best_gain=gain
                best_criteria=(col,value)
                best_sets=(set1,set2)
            # gradenje na podgrankite
        if best_gain>0:
            # bidejki dobivkata e >0 mozna e druga podelba
            trueBranch=buildtree(best_sets[0])
            falseBranch=buildtree(best_sets[1])
            return decisionnode(col=best_criteria[0],value=best_criteria[1], tb=trueBranch, fb=falseBranch)
        else:
            #ne e mozna druga podelba bidejki site instanci vo ova mnozestvo ja imaat istata klasa
            #rezultato sto ke se vrati ke sodrzi recnik so kluc klasata koja se predviduva
            #vo ovoj list i vrednost kolku pati bila sretnata vo trening mnozestvoto
            return decisionnode(results=uniquecounts(rows))