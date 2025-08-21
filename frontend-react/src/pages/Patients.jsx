import React, { useEffect, useState } from 'react'
import axios from '../utils/axios'

export default function Patients(){
  const [list, setList] = useState([])
  const [form, setForm] = useState({ fullName:'', age:0, history:'' })

  const load = async () => {
    const { data } = await axios.get('/patient/api/v1/patients')
    setList(data)
  }
  useEffect(()=>{ load() }, [])

  const add = async (e) => {
    e.preventDefault()
    await axios.post('/patient/api/v1/patients', form)
    setForm({ fullName:'', age:0, history:'' })
    load()
  }

  return (
    <div>
      <h2>Patients</h2>
      <form onSubmit={add} style={{display:'grid', gap:6, maxWidth:420}}>
        <input placeholder="Full Name" value={form.fullName} onChange={e=>setForm({...form, fullName:e.target.value})}/>
        <input placeholder="Age" type="number" value={form.age} onChange={e=>setForm({...form, age:+e.target.value})}/>
        <textarea placeholder="History" value={form.history} onChange={e=>setForm({...form, history:e.target.value})}/>
        <button>Add</button>
      </form>
      <ul>
        {list.map(p=>(<li key={p.id}>{p.fullName} — {p.age} yrs — {p.history}</li>))}
      </ul>
    </div>
  )
}
